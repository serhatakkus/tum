package pgdp.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.stream.IntStream.range;
import static java.util.stream.Stream.concat;

public class CollisionBoard {
    private final int sizeX;
    private final int sizeY;
    private final int bucketXSize;
    private final int bucketYSize;
    private final int bucketsX;
    private final List<Entity>[] buckets;

    /**
     * Erstellt ein neues CollisionBoard.
     * Alle Parameter müssen >0 sein.
     *
     * @param sizeX       x Grüße des Feldes
     * @param sizeY       y Größe des Feldes
     * @param bucketXSize muss <= sizex sein
     * @param bucketYSize muss <= sizey sein
     */
    public CollisionBoard(int sizeX, int sizeY, int bucketXSize, int bucketYSize) {
        if (bucketXSize < 1 || bucketYSize < 1 || bucketXSize > sizeX || bucketYSize > sizeY) {
            throw new IllegalArgumentException();
        }
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.bucketXSize = bucketXSize;
        this.bucketYSize = bucketYSize;
        bucketsX = (sizeX % bucketXSize == 0 ? 0 : 1) + sizeX / bucketXSize;
        int bucketsY = (sizeY % bucketYSize == 0 ? 0 : 1) + sizeY / bucketYSize;
        buckets = new List[bucketsX * bucketsY];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    private void removeEmpty(){
        if(toRemoveEmpty) {
            System.err.println("removing empty entity");
            Arrays.stream(buckets).iterator().forEachRemaining(l -> l.removeIf(e -> e.getHitBox().isEmpty()));
            toRemoveEmpty=false;
        }

    }
    private boolean toRemoveEmpty=false;

    /**
     * Fügt ein weiteres Entity zum Board hinzu.
     * Wenn der durch die HitBox belegte Bereich nicht frei ist wird ein neuer Platz gesucht und die Position der
     * HitBox wird entsprechend angepasst.
     * Wenn kein Platz für das Entity gefunden werden konnte oder es zumindest teilweise außerhalb des Feldes liegt,
     * soll false zurückgegeben werden.
     *
     * @param entity 🧠
     * @return 🧠
     */
    public boolean set(Entity entity) {
        if (entity.getHitBox().isEmpty()) {
            return false;
        }
        var hitBox = entity.getHitBox().get();
        var minX = hitBox.getPosition().getX();
        var minY = hitBox.getPosition().getY();
        var maxX = minX + hitBox.getBoundingBox().getWidth();
        var maxY = minY + hitBox.getBoundingBox().getHeight();
        //check board bounds
        if (minX < 0 || maxX >= sizeX || minX > maxX || minY < 0 || maxY >= sizeY || minY > maxY) {
            return false;
        }
        var position = concat(
                Stream.of(hitBox.getPosition()),
                range(1, max(max(minX, minY), max(sizeX - 1 - maxX, sizeY - 1 - maxY)))
                        .boxed()
                        .flatMap(range -> getPositionStream(hitBox, minX, minY, maxX, maxY, range))
        )
                .filter(p -> fieldCollisions(new LocatedBoundingBox(p, hitBox.getBoundingBox()), entity).findAny()
                        .isEmpty())
                .findFirst();
        if (position.isPresent()) {
            hitBox.setPosition(position.get());
            int lowXSelf = hitBox.getPosition().getX();
            int lowYSelf = hitBox.getPosition().getY();
            int highXSelf = lowXSelf + hitBox.getBoundingBox().getWidth();
            int highYSelf = lowYSelf + hitBox.getBoundingBox().getHeight();
            int lowerXBucket = lowXSelf / bucketXSize;
            int lowerYBucket = lowYSelf / bucketYSize;
            int upperXBucket = highXSelf / bucketXSize;
            int upperYBucket = highYSelf / bucketYSize;
            for (int x = lowerXBucket; x <= upperXBucket; x++) {
                for (int y = lowerYBucket; y <= upperYBucket; y++) {
                    buckets[x + y * bucketsX].add(entity);
                }
            }
            removeEmpty();
            return true;
        } else {
            removeEmpty();
            return false;
        }
    }

    private Stream<Position> getPositionStream(
            LocatedBoundingBox hitBox, int minX, int minY, int maxX, int maxY, Integer range
    ) {
        var stream = maxX + range >= sizeX ? Stream.<Position>empty()
                : range(
                max(0, minY - range),
                min(sizeY, maxY + range + 1) - hitBox.getBoundingBox().getHeight()
        )
                .mapToObj(y -> new Position(minX + range, y));
        if (maxY + range < sizeY) {
            stream = concat(
                    stream,
                    reverseRange(
                            min(sizeX, maxX + range) - hitBox.getBoundingBox().getWidth(),
                            max(-1, minX - 1 - range)
                    )
                            .mapToObj(x -> new Position(x, minY + range))
            );
        }
        if (minX - range > -1) {
            stream = concat(
                    stream,
                    reverseRange(
                            min(sizeY, maxY + range) - hitBox.getBoundingBox().getHeight(),
                            max(-1, minY - range - 1)
                    ).mapToObj(y -> new Position(minX - range, y))
            );
        }
        if (minY - range > -1) {
            stream = concat(
                    stream,
                    range(
                            max(0, minX - range + 1),
                            min(sizeX, maxX + range - 1) - hitBox.getBoundingBox()
                                    .getWidth()
                    ).mapToObj(x -> new Position(x, minY - range))
            );
        }
        return stream;
    }

    /**
     * Internal stream based iteration of Collisions.
     * Streams will be discussed in later weeks.
     *
     * @param bounds the current bounds
     * @param self   the current entity 🧠
     * @return collisions 🧠
     */
    private Stream<Entity> fieldCollisions(LocatedBoundingBox bounds, Entity self) {
        int lowXSelf = max(bounds.getPosition().getX(), 0);
        int lowYSelf = max(bounds.getPosition().getY(), 0);
        int highXSelf = min(lowXSelf + bounds.getBoundingBox().getWidth(), sizeX - 1);
        int highYSelf = min(lowYSelf + bounds.getBoundingBox().getHeight(), sizeY - 1);
        int lowerXBucket = lowXSelf / bucketXSize;
        int lowerYBucket = lowYSelf / bucketYSize;
        int upperXBucket = highXSelf / bucketXSize;
        int upperYBucket = highYSelf / bucketYSize;
        return concat(
                //first handle buckets completely inside the bounding box
                range(lowerXBucket + 1, upperXBucket)
                        .boxed()
                        .flatMap(xBucket ->
                                range(lowerYBucket + 1, upperYBucket)
                                        .mapToObj(yBucket -> buckets[yBucket * bucketsX + xBucket])
                                        .flatMap(List::stream)
                        ),
                //handle boundaries where true collision checking needs to be Performed
                concat(
                        //handle lower x line
                        range(lowerXBucket, upperXBucket + 1)
                                .mapToObj(xBucket -> buckets[lowerYBucket * bucketsX + xBucket]),
                        //handle non line bucket matches
                        lowerYBucket == upperYBucket

                                //is single x line
                                ? Stream.empty()
                                //has at least 2 affected y buckets
                                : concat(
                                //upper x line
                                range(lowerXBucket, upperXBucket + 1)
                                        .mapToObj(xBucket -> buckets[upperYBucket * bucketsX + xBucket]),
                                //buckets enclosed by the bounding Box exist
                                upperYBucket - lowerYBucket < 2
                                        //max 2 y lines
                                        ? Stream.empty()
                                        //enclosed Bounding box, iter remaining borders
                                        : range(lowerYBucket + 1, upperYBucket).boxed()
                                        .flatMap(yBucket -> {
                                            int y = yBucket * bucketsX;
                                            return Stream.of(
                                                    buckets[y + lowerXBucket],
                                                    buckets[y + upperXBucket]
                                            );
                                        })
                        )

                ).flatMap(List::stream)
                        .filter(other -> {
                            if (other.getHitBox().isEmpty()) {
                                toRemoveEmpty=true;
                                return false;
                            }
                            var otherBox = other.getHitBox().get();
                            int lowXOther = otherBox.getPosition().getX();
                            int highXOther = lowXOther + otherBox.getBoundingBox().getWidth();
                            int lowYOther = otherBox.getPosition().getY();
                            int highYOther = lowYOther + otherBox.getBoundingBox().getHeight();
                            return
                                    //x collides
                                    (
                                            lowXOther < highXSelf && lowXOther >= lowXSelf
                                             || lowXSelf < highXOther && lowXSelf >= lowXOther
                                    )
                                            //y collides
                                            && (
                                                    lowYOther < highYSelf && lowYOther >= lowYSelf
                                                        || lowYSelf < highYOther && lowYSelf >= lowYOther
                                    );
                        })
                //prevent self match
        ).filter(e -> e != self);

    }

    private IntStream reverseRange(int from, int to) {
        return from > to ? range(0, from - to).map(i -> from - i) : IntStream.empty();
    }

    /**
     * wenn das Entity eine HitBox hat, wird es aus dem CollisionBoard entfernt.
     * Dabei wird auch die hitBox des Entity geleert
     *
     * @param entity 🧠
     */
    public void remove(Entity entity) {
        entity.getHitBox().ifPresent(hitBox -> {
            entity.setHitBox(null);
            int lowXSelf = hitBox.getPosition().getX();
            int lowYSelf = hitBox.getPosition().getY();
            int highXSelf = lowXSelf + hitBox.getBoundingBox().getWidth();
            int highYSelf = lowYSelf + hitBox.getBoundingBox().getHeight();
            int lowerXBucket = lowXSelf / bucketXSize;
            int lowerYBucket = lowYSelf / bucketYSize;
            int upperXBucket = highXSelf / bucketXSize;
            int upperYBucket = highYSelf / bucketYSize;
            for (int x = lowerXBucket; x <= upperXBucket; x++) {
                for (int y = lowerYBucket; y <= upperYBucket; y++) {
                    buckets[x + y * bucketsX].remove(entity);
                }
            }
        });
        removeEmpty();
    }

    /**
     * Gibt alle Entities zurück, mit denen bei dieser Bewegung Kollisionen auftreten würden.
     * Jedes Entity ist nur einmal enthalten.
     *
     * @param entity    🧠
     * @param direction 🧠
     * @return 🧠
     */
    public List<Entity> getCollisions(Entity entity, Direction direction) {
        var r=entity.getHitBox().stream().flatMap(hitBox -> fieldCollisions(
                        new LocatedBoundingBox(move(hitBox.getPosition(), direction), hitBox.getBoundingBox()), entity
                ))
                .distinct()
                .toList();
        removeEmpty();
        return r;

    }

    private Position move(Position p, Direction d) {
        return new Position(
                switch (d) {
                    case LEFT, DOWN_LEFT, UP_LEFT -> p.getX() - 1;
                    case RIGHT, DOWN_RIGHT, UP_RIGHT -> p.getX() + 1;
                    case DOWN, UP -> p.getX();
                },
                switch (d) {
                    case UP, UP_LEFT, UP_RIGHT -> p.getY() - 1;
                    case DOWN, DOWN_LEFT, DOWN_RIGHT -> p.getY() + 1;
                    case LEFT, RIGHT -> p.getY();
                }
        );
    }

    /**
     * Gibt zurück, ob eine Collision mit einem entity oder den Spielfeldrändern erfolgen würde.
     *
     * @param entity    🧠
     * @param direction 🧠
     * @return 🧠
     */
    public boolean hasCollisions(Entity entity, Direction direction) {
        var o = entity.getHitBox();
        if (o.isEmpty()) {
            return false;
        }
        var hitBox = new LocatedBoundingBox(move(o.get().getPosition(), direction), o.get().getBoundingBox());
        var minX = hitBox.getPosition().getX();
        var minY = hitBox.getPosition().getY();
        var maxX = minX + hitBox.getBoundingBox().getWidth();
        var maxY = minY + hitBox.getBoundingBox().getHeight();
        //check board bounds
        if (minX < 0 || maxX >= sizeX || minX > maxX || minY < 0 || maxY >= sizeY || minY > maxY) {
            return true;
        }
        var r= fieldCollisions(
                hitBox, entity
        ).findAny().isPresent();
        removeEmpty();
        return r;
    }

    /**
     * Versucht das Entity in die entsprechende Richtung zu bewegen.
     * Wenn die Bewegung diagonal ist, wird geprüft,
     * ob eine Bewegung nur in x oder y Richtung nicht blockiert wird,
     * und wenn möglich umgesetzt.
     * Wenn eine Bewegung möglich ist, wird sowohl das Board als auch die HitBox angepasst.
     * Dabei gelten die Grenzen des Feldes auch als Kollisionen.
     *
     * @param entity    🧠
     * @param direction 🧠
     */
    public void move(Entity entity, Direction direction) {

        entity.getHitBox().ifPresent(hitBox -> {
            var newPosition = move(hitBox.getPosition(), direction);

            if (fieldCollisions(new LocatedBoundingBox(newPosition, hitBox.getBoundingBox()), entity).findAny()
                    .isEmpty()) {
                int lowXSelf = hitBox.getPosition().getX();
                int lowYSelf = hitBox.getPosition().getY();
                int highXSelf = lowXSelf + hitBox.getBoundingBox().getWidth();
                int highYSelf = lowYSelf + hitBox.getBoundingBox().getHeight();
                int lowerXBucket = lowXSelf / bucketXSize;
                int lowerYBucket = lowYSelf / bucketYSize;
                int upperXBucket = highXSelf / bucketXSize;
                int upperYBucket = highYSelf / bucketYSize;
                int nLowXSelf = newPosition.getX();
                int nLowYSelf = newPosition.getY();
                int nHighXSelf = nLowXSelf + hitBox.getBoundingBox().getWidth();
                int nHighYSelf = nLowYSelf + hitBox.getBoundingBox().getHeight();
                int nLowerXBucket = nLowXSelf / bucketXSize;
                int nLowerYBucket = nLowYSelf / bucketYSize;
                int nUpperXBucket = nHighXSelf / bucketXSize;
                int nUpperYBucket = nHighYSelf / bucketYSize;
                if (nLowXSelf < 0 || nHighXSelf >= sizeX || nLowXSelf > nHighXSelf || nLowYSelf < 0
                        || nHighYSelf >= sizeY || nLowYSelf > nHighYSelf) {
                    return;
                }
                switch (direction) {
                    case UP, UP_LEFT, UP_RIGHT -> {
                        if (upperYBucket != nUpperYBucket) {
                            for (int i = lowerXBucket; i <= upperXBucket; i++) {
                                buckets[i + upperYBucket * bucketsX].remove(entity);
                            }
                        }
                        if (direction == Direction.UP_LEFT && upperXBucket != nUpperXBucket) {
                            for (int i = lowerYBucket; i < upperYBucket; i++) {
                                buckets[i * bucketsX + upperXBucket].remove(entity);
                            }
                            if (upperYBucket == nUpperYBucket) {
                                buckets[upperYBucket * bucketsX + upperXBucket].remove(entity);
                            }
                        } else if (direction == Direction.UP_RIGHT && lowerXBucket != nLowerXBucket) {
                            for (int i = lowerYBucket; i < upperYBucket; i++) {
                                buckets[i * bucketsX + lowerXBucket].remove(entity);
                            }
                            if (upperYBucket == nUpperYBucket) {
                                buckets[upperYBucket * bucketsX + lowerXBucket].remove(entity);
                            }
                        }
                    }
                    case DOWN, DOWN_LEFT, DOWN_RIGHT -> {
                        if (lowerYBucket != nLowerYBucket) {
                            for (int i = lowerXBucket; i <= upperXBucket; i++) {
                                buckets[i + lowerYBucket * bucketsX].remove(entity);
                            }
                        }
                        if (direction == Direction.DOWN_LEFT && upperXBucket != nUpperXBucket) {
                            for (int i = lowerYBucket; i < upperYBucket; i++) {
                                buckets[i * bucketsX + upperXBucket].remove(entity);
                            }
                            if (lowerYBucket == nLowerYBucket) {
                                buckets[lowerYBucket * bucketsX + upperXBucket].remove(entity);
                            }
                        } else if (direction == Direction.DOWN_RIGHT && lowerXBucket != nLowerXBucket) {
                            for (int i = lowerYBucket; i < upperYBucket; i++) {
                                buckets[i * bucketsX + lowerXBucket].remove(entity);
                            }
                            if (lowerYBucket == nLowerYBucket) {
                                buckets[lowerYBucket * bucketsX + lowerXBucket].remove(entity);
                            }
                        }
                    }
                    case LEFT -> {
                        if (upperXBucket != nUpperXBucket) {
                            for (int i = lowerYBucket; i <= upperYBucket; i++) {
                                buckets[i * bucketsX + upperXBucket].remove(entity);
                            }
                        }
                    }
                    case RIGHT -> {
                        if (lowerXBucket != nLowerXBucket) {
                            for (int i = lowerYBucket; i <= upperYBucket; i++) {
                                buckets[i * bucketsX + lowerXBucket].remove(entity);
                            }
                        }
                    }
                    default -> {
                        throw new IllegalStateException("exhaustive match");
                    }
                }
                switch (direction) {
                    case DOWN, DOWN_LEFT, DOWN_RIGHT -> {
                        if (upperYBucket != nUpperYBucket) {
                            for (int i = nLowerXBucket; i <= nUpperXBucket; i++) {
                                buckets[i + nUpperYBucket * bucketsX].add(entity);
                            }
                        }
                        if (direction == Direction.DOWN_RIGHT && upperXBucket != nUpperXBucket) {
                            for (int i = nLowerYBucket; i < nUpperYBucket; i++) {
                                buckets[i * bucketsX + nUpperXBucket].add(entity);
                            }
                            if (upperYBucket == nUpperYBucket) {
                                buckets[nUpperYBucket * bucketsX + nUpperXBucket].add(entity);
                            }
                        } else if (direction == Direction.DOWN_LEFT && lowerXBucket != nLowerXBucket) {
                            for (int i = nLowerYBucket; i < nUpperYBucket; i++) {
                                buckets[i * bucketsX + nLowerXBucket].add(entity);
                            }
                            if (upperYBucket == nUpperYBucket) {
                                buckets[nUpperYBucket * bucketsX + nLowerXBucket].add(entity);
                            }
                        }
                    }
                    case UP, UP_LEFT, UP_RIGHT -> {
                        if (lowerYBucket != nLowerYBucket) {
                            for (int i = nLowerXBucket; i <= nUpperXBucket; i++) {
                                buckets[i + nLowerYBucket * bucketsX].add(entity);
                            }
                        }
                        if (direction == Direction.UP_RIGHT && upperXBucket != nUpperXBucket) {
                            for (int i = nLowerYBucket; i < nUpperYBucket; i++) {
                                buckets[i * bucketsX + nUpperXBucket].add(entity);
                            }
                            if (lowerYBucket == nLowerYBucket) {
                                buckets[nLowerYBucket * bucketsX + nUpperXBucket].add(entity);
                            }
                        } else if (direction == Direction.UP_LEFT && lowerXBucket != nLowerXBucket) {
                            for (int i = nLowerYBucket; i < nUpperYBucket; i++) {
                                buckets[i * bucketsX + nLowerXBucket].add(entity);
                            }
                            if (lowerYBucket == nLowerYBucket) {
                                buckets[lowerYBucket * bucketsX + lowerXBucket].add(entity);
                            }
                        }
                    }
                    case RIGHT -> {
                        if (upperXBucket != nUpperXBucket) {
                            for (int i = nLowerYBucket; i <= nUpperYBucket; i++) {
                                buckets[i * bucketsX + nUpperXBucket].add(entity);
                            }
                        }
                    }
                    case LEFT -> {
                        if (lowerXBucket != nLowerXBucket) {
                            for (int i = nLowerYBucket; i <= nUpperYBucket; i++) {
                                buckets[i * bucketsX + nLowerXBucket].add(entity);
                            }
                        }
                    }
                    default -> {
                        throw new IllegalStateException("exhaustive match");
                    }
                }
                hitBox.setPosition(newPosition);
            }
        });
        removeEmpty();
    }

}

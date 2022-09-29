package tumtraining.recursion.fs;

public class Main {

	public static void main(String[] args) {
		Folder home = new Folder("home", new FSElement[] { new File("a"), new File("b") });
		Folder etc = new Folder("etc", new FSElement[] { new File("q"), new Link("home", home) });
		Folder root = new Folder("root", new FSElement[] { new File("test"), home, etc });	
		Folder newFolder = new Folder("newFolder", null);
		
		System.out.println(root.toString());
		
		root.add(newFolder, "root/etc/home");
		
		System.out.println(root.toString());
		

		root.add(new File("cv3"), "root/etc/home");
		root.add(new File("cv"), "root/etc/home/newFolder");
		root.add(new File("cv1"), "root/etc/home/newFolder");
		root.add(new File("cv2"), "root/etc/home/newFolder");

		System.out.println(root.toString());
		
		root.forEach(fs -> System.out.println(fs.getName()));
	}

}

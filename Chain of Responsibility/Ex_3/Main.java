import java.util.*;

public class Main {
  public static void main(String[] args) {

    PlaylistImpl playlist = new PlaylistImpl();

    playlist.addSong(new Song("Lemon Tree", "Fools Garden"));
    playlist.addSong(new Song("Stressed Out", "Twenty One Pilots"));
    playlist.addSong(new Song("Payphone", "Maroon5"));

    Iterator iterator = playlist.createIteratorForward();

    System.out.println("Forward Iterator:");
    while (iterator.hasNext()) {
      Song currentSong = iterator.next();
      System.out.println("Title: \"" + currentSong.getTitle() + "\". " + " " + "Artist: \"" + currentSong.getArtist() + "\".");
    }
    System.out.println();

    iterator = playlist.createIteratorBackward();

    System.out.println("Backward Iterator:");
    while (iterator.hasNext()) {
      Song currentSong = iterator.next();
      System.out.println("Title: \"" + currentSong.getTitle() + "\". " + " " + "Artist: \"" + currentSong.getArtist() + "\".");
    }
    System.out.println();

    iterator = playlist.createIteratorShuffle();

    System.out.println("Shuffle Iterator:");
    while (iterator.hasNext()) {
      Song currentSong = iterator.next();
      System.out.println("Title: \"" + currentSong.getTitle() + "\". " + " " + "Artist: \"" + currentSong.getArtist() + "\".");
    }
    System.out.println();
  }
}

interface Iterator{
  public boolean hasNext();
  public Song next();
}

interface Playlist{
  public Iterator createIteratorForward();
  public Iterator createIteratorBackward();
  public Iterator createIteratorShuffle();
  public void addSong(Song song);
}

class Song{
  private String title;
  private String artist;

  public Song(String title, String artist){
    this.title = title;
    this.artist = artist;
  }

  public String getTitle(){
    return this.title;
  }

  public String getArtist(){
    return this.artist;
  }
}

class PlaylistImpl implements Playlist{
  List<Song> list = new ArrayList<>();

  public Iterator createIteratorForward(){
    return new ForwardIterator(this);
  }

  public Iterator createIteratorBackward(){
    return new BackIterator(this);
  }

  public Iterator createIteratorShuffle(){
    return new ShuffleIterator(this);
  }

  public void addSong(Song song){
    this.list.add(song);
  }

  public List<Song> getList(){
    return this.list;
  }
}

class ForwardIterator implements Iterator{

  private PlaylistImpl playlist;
  private int position;
  ArrayList<Song> forwardList = new ArrayList<>();

  public ForwardIterator(PlaylistImpl playlist){
    this.playlist = playlist;
    for (Song song : playlist.getList()) {
      this.forwardList.add(song);
    }
  }

  public boolean hasNext(){
    if(this.position < this.forwardList.size()){
      return true;
    }
    return false;
  }

  public Song next(){
    return forwardList.get(this.position++);
  }
}

class BackIterator implements Iterator{

  private PlaylistImpl playlist;
  private int position;
  ArrayList<Song> backList = new ArrayList<>();

  public BackIterator(PlaylistImpl playlist){
    this.playlist = playlist;
    for (Song song : playlist.getList()) {
      this.backList.add(song);
    }
    this.position = playlist.getList().size() - 1;
  }

  public boolean hasNext(){
    if(this.position > -1){
      return true;
    }
    return false;
  }

  public Song next(){
    return backList.get(this.position--);
  }
}

class ShuffleIterator implements Iterator{

  private PlaylistImpl playlist;
  private int position;
  ArrayList<Song> shuffleList = new ArrayList<>();

  public ShuffleIterator(PlaylistImpl playlist){
    this.playlist = playlist;
    for (Song song : playlist.getList()) {
      this.shuffleList.add(song);
    }
    Collections.shuffle(this.shuffleList);
  }

  public boolean hasNext(){
    if(this.position < this.shuffleList.size()){
      return true;
    }
    return false;
  }

  public Song next(){
    return shuffleList.get(this.position++);
  }
}



















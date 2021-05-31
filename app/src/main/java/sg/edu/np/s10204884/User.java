package sg.edu.np.s10204884;

public class User {
    private String Name;
    private String Description;
    private int Id;
    private boolean Followed = false;

    public void setName(String name) {
        this.Name = name;
    }
    public String getName(){
        return Name;
    }
    public void setDescription(String desc){
        this.Description = desc;
    }
    public String getDescription(){
        return Description;
    }
    public void setId(int id){
        this.Id = id;
    }
    public int getId(){
        return Id;
    }
    public void setFollowed(boolean followed){
        this.Followed = followed;
    }
    public boolean getFollowed(){
        return Followed;
    }


}

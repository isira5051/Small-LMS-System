import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class beautifulJuice
{
    
   
    
    static headmaster h;
    
    public static void main(String[] args) throws Exception
    {
        ArrayList<person> list = new ArrayList<>();
        serializeData ser = new serializeData();
        Scanner input = new Scanner(System.in);
        adminPerson adminPerson = new adminPerson();
        teacherPerson teacherPerson = new teacherPerson();
        loginClass loginClass = new loginClass();
        Data data = new Data();
        person a = new admin("induwara","200031701421","Pizzahut1234");//////creating an admin////////
        boolean nullVal = false;
        try {
            list = ser.deSerialize();
        } catch (Exception e) {
            
           list.add(a);
           ser.serialize(list);
        }

        loginClass.login();
        
    
    }
    
}

class loginClass
{
    public void login() throws Exception
    {
        ArrayList<person> list = new ArrayList<>();
        serializeData ser = new serializeData();
        Scanner input = new Scanner(System.in);
        adminPerson adminPerson = new adminPerson();
        teacherPerson teacherPerson = new teacherPerson();
        try {
            list = ser.deSerialize();
        } catch (Exception e) {
            System.out.println("No data");
           
        }
        System.out.println("*****************************************************************************************************************************************************************************");
        System.out.println("                                                  Welcome To Isira's LMS management system");
        System.out.println("*****************************************************************************************************************************************************************************");
        System.out.println();
        System.out.println("Login to Continue");
        System.out.println();
        String name = input.nextLine();
        String pass = input.next();

        interfaceDisplay  interfaceDisplay = new interfaceDisplay();
        String check = interfaceDisplay.authenticate(name, pass, list);
        
        System.out.println();
        if(check != "")
        {
            System.out.println("*******You are logged in as ********   "+check);
            System.out.println();
            System.out.println();
            
        }
        else
        {
            System.out.println("Invalid Details!");
        }

        if(check.equals("admin"))
        {
            adminPerson.menu();
        }

        else if(check.equals("teacher"))
        {
            teacherPerson.getDetails(name);
            teacherPerson.menu(name , list);
            

        }

        else if(check.equals("student"))
        {
            studentMenu studentMenu = new studentMenu();
            studentMenu.menu(name);
        }
    }
}


interface person extends Serializable
{
    public String getName();
    public int getAge();
    public String getNic();
    public String getPass();
    public person getPerson();
    public int getSalary();

}



class Data
{
    

    public void displayStudent(ArrayList<person> list)
    {
        for(person p : list)
        {
            if(p instanceof student)
            {
                System.out.print(p.getName() + " "+p.getAge()+ " "+p.getNic()+" ");
                System.out.println();
            }
        }

    }
    public void displayTeachers(ArrayList<person> list)
    {
         for(person p : list)
        {
            if(p instanceof teacher)
            {
                System.out.print(p.getName() + " "+p.getAge()+ " "+p.getNic()+" ");
                System.out.println();
            }
        }

    }
    public void displayAdmin(ArrayList<person> list)
    {
         for(person p : list)
        {
            if(p instanceof admin)
            {
                System.out.print(p.getName() + " "+p.getAge()+ " "+p.getNic()+" "+p.getPass());
                System.out.println();
            }
        }

    }
}

class student implements person
{

    private String name;
    private String nic;
    private int age;
    private String pass;
    private student s;

    public student(String name , String nic , int age , String pass)
    {
        this.name = name;
        this.nic = nic;
        this.age = age;
        this.pass = pass;

    }

    @Override
    public String getName() {
        
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getNic() {
       
        return nic;
    }
    @Override
    public String getPass() {
       
        return pass;
    }

    @Override
    public person getPerson() {
        
        return s;
    }

    @Override
    public int getSalary() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSalary'");
    }

   

    
}


class teacher implements person 
{
    private String name;
    private String nic;
    private int age;
    private int salary;
    private String pass;

    public teacher(String name , String nic , int age  , int salary , String pass)
    {
        this.name = name;
        this.nic = nic;
        this.age = age;
        this.salary = salary;
        this.pass = pass;

    }

    @Override
    public String getName() {
        
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getNic() {
       
        return nic;
    }

    @Override
    public String getPass() {
       
        return pass;
    }

    


    public int getSalary() {
        return salary;
    }

    @Override
    public person getPerson() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPerson'");
    }

   
}

class headmaster 
{
    private String name;
    private String nic;
    private int age;
    private int salary;
    private static headmaster headmaster;

    private headmaster()
    {
        

    }

    public static headmaster createHeadmaster()
    {
        if(headmaster == null)
        {
            headmaster = new headmaster();
        }


        return headmaster;


    }

    
    public String getName() {
        
        return name;
    }

    
    public int getAge() {
        return age;
    }

   
    public String getNic() {
       
        return nic;
    }

    

    public void setName(String name)
    {
        this.name = name;

    }
    public void setSalary(int salary)
    {
        this.salary = salary;
    }
    public void setAge(int age)
    {
        this.age = age;

    }

    public void setNic(String nic)
    {
        this.nic = nic;
    }

   

    public int getSalary() {
        return salary;
    }

    
}


class admin implements person
{

    private String name;
    private String nic;
    private String pass;

    public admin(String name , String nic  , String pass)
    {
        this.name = name;
        this.nic = nic;
        this.pass = pass;

    }

    @Override
    public String getName() {
        
        return name;
    }

    @Override
    public int getAge() {
        return 0;
    }

    @Override
    public String getNic() {
       
        return nic;
    }

    @Override
    public String getPass() {
       
        return pass;
    }

    @Override
    public person getPerson() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPerson'");
    }

    @Override
    public int getSalary() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSalary'");
    }
    
}



class serializeData {
    ArrayList<person> list = new ArrayList<>();

    public void serialize(ArrayList<person> getlist) throws Exception {
        FileOutputStream fos = new FileOutputStream("entryData.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(getlist); 
        oos.close();
    }

    public ArrayList<person> deSerialize() throws Exception {
        FileInputStream fis = new FileInputStream("entryData.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object temp = ois.readObject();
        
        list = (ArrayList<person>) temp;
        ois.close();
        return list;
    }
}
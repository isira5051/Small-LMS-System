import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class interfaceDisplay 
{

    public String authenticate(String name , String pass  , ArrayList<person> list)
    {
        for(person p : list)
        {
           
            if(p.getName().equals(name) && p.getPass().equals(pass))
            {
                if(p instanceof student)
                {
                    return "student";
                }

                else if(p instanceof teacher)
                {
                    return "teacher";
                }
                else if(p instanceof admin)
                {
                    return "admin";
                }
                
            }
        }


        return "";
    }
    
}



class adminPerson


{
    ArrayList<person> list = new ArrayList<>();
    ArrayList<person> DisplayList = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    

    public void menu() throws Exception
    {
        while(true)
        {
            AddDetails addDetails  = new AddDetails();
            System.out.println();
            System.out.println("1. Add Data  2. View Details   3. Remove Data   5. Sign Out");
            int num = input.nextInt();
            if(num == 1)
            {
                addDetails.add();
            }
            
            else if(num == 2 )
            {
               EditData editData = new EditData();
               editData.AdminData();

            }
             else if(num == 3 )
            {
               removedataList removedataList = new removedataList();
               removedataList.removePersonList();

            }

            else if (num == 5)
            {
                break;
            }

            
        }
        System.out.println();
        System.out.println();
        System.out.println();
        loginClass loginClass = new loginClass();
        loginClass.login();

        
    }

    
       

    

}


class teacherPerson
{   
    
   
    Scanner input = new Scanner(System.in);

    public void getDetails(String name) {
        LocalDate currentDate = LocalDate.now();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        System.out.println("*********Welcome ********* " + name + " on " + formattedDate + " *****");
    }

    public void menu(String name , ArrayList<person> list) throws Exception
    {
        teacherFunctions teacherFunctions = new teacherFunctions();
        while(true)
        {
            System.out.println();
            System.out.println("1. View Notices          2. Send Notice          3. Set Subjects       4. Edit subject data     5. Sign Out");
        int num = input.nextInt();
        if(num == 1)
        {
            teacherFunctions.viewnotice(name);
        }
        else if(num == 2)
        {
            teacherFunctions.addNotice(name);
        }
        else if(num == 3)
        {
            subjectWater subjectWater = new subjectWater();
            subjectWater.menu();


        }
        else if(num == 4)
        {
            removedataList removedataList = new removedataList();
            removedataList.printSubjectList();
            removedataList.removeSubjectList();
            removedataList.printSubjectList();
            
            


        }
        else if(num == 5)
        {
            break;
        }

        }

         System.out.println();
        System.out.println();
        System.out.println();
        loginClass loginClass = new loginClass();
        loginClass.login();
       
    }

    
    
}


class teacherFunctions
{
    ArrayList<notice> noticeList = new ArrayList<>();
    boolean availableNotice = true;
    Scanner input = new Scanner(System.in);

    

    public void addNotice(String name) throws Exception
    {
        LocalDate currentDate = LocalDate.now();
        System.out.println();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        System.out.println("subject  content");
        String subject = input.next();
        String contennt = input.nextLine();

        SerializeNoticeList serNoticeList = new SerializeNoticeList();
        try {
            noticeList = serNoticeList.deSerialize();
        } catch (Exception e) {
            availableNotice = false;
        }

        notice newNotice = new notice(name, subject, contennt, formattedDate);
        noticeList.add(newNotice);
        serNoticeList.Serialize(noticeList);
        

    }

    public void viewnotice(String name)
    {
        System.out.println();
        System.out.println();
        ArrayList<notice> noticeListTemp = new ArrayList<>();
        SerializeNoticeList serNoticeList = new SerializeNoticeList();
        try {
            noticeListTemp = serNoticeList.deSerialize();
        } catch (Exception e) {
            System.out.println("Nothing at the Moment");
        }
        if(noticeListTemp.size() != 0)
        {
            for(notice notice : noticeListTemp)
        {
            if(notice.getName().equals(name))
            {
                System.out.println();
                System.out.println(notice.getDate());
                System.out.println();
                System.out.println(notice.getSubject()+"  -->  ");
                System.out.println();
                System.out.println("*****************************************************************************************************************************************************************************");
                System.out.println(notice.getContent());
                System.out.println("*****************************************************************************************************************************************************************************");
            }
        }
        }
        
        System.out.println();


    }

}



class AddDetails
{
    ArrayList<person> list = new ArrayList<>();
    ArrayList<person> DisplayList = new ArrayList<>();
    Scanner input = new Scanner(System.in);
      public void add() throws Exception
      {
        headmaster h;
       

       System.out.println();
        System.out.println("type name nic age salary");
      
       serializeData ser = new serializeData();
       Data d = new Data();

       list = ser.deSerialize();
    
       String type = "";
       
         try {
             type = input.next();
       } catch (Exception e) {
            System.out.println(e);
       }
       if(type.equals("student"))
       {
            try {
                String name = input.nextLine();
                String nic = input.next();
                int age = input.nextInt();
                String password = input.next();
                person s = new student(name , nic , age , password);
                list.add(s);
                System.out.println("added!");
            } catch (Exception e) {
                System.out.println(e);
            }
            
       }

       else  if(type.equals("teacher"))
       {
            try {
                String name = input.nextLine();
                String nic = input.next();
                int age = input.nextInt();
                int salary = input.nextInt();
                String  password = input.next();

            person  t = new teacher(name, nic, age, salary , password);
            list.add(t);
            System.out.println("added!");
            } catch (Exception e) {
                System.out.println(e);
            }
           
       }
       else  if(type.equals("headmaster"))
       {
            String name = input.nextLine();
            String nic = input.next();
            int age = input.nextInt();
            int salary = input.nextInt();

            h = headmaster.createHeadmaster();
            h.setName(name);
            h.setNic(nic);
            h.setAge(age);
            h.setSalary(salary);
            System.out.println("added!");
            System.out.println();
            
           
       }
       

       else if(type.equals(""))
       {
        
            System.out.println("Enter Data!");
       }
       

       else if(type.equals("print"))
       {
           String type2 = input.next();
           DisplayList = ser.deSerialize();

           if(type2.equals("student"))
           {
            d.displayStudent(DisplayList);
           }

           else if(type2.equals("teacher"))
           {
            d.displayTeachers(DisplayList);
           }
       }

       else
       {
            System.out.println("wrong data!");
            System.out.println();
       }

       ser.serialize(list);
       
       }


       public void viewData()
       { 
        boolean proceed = true;
        serializeData ser = new serializeData();

         try {
            list = ser.deSerialize();
        } catch (Exception e) {
            System.out.println("No Data!");
            proceed = false;
        }

        if(proceed)
        {
            System.out.println();
            System.out.println();
            for(int i = 0 ; i < list.size() ; i ++)
            {
                System.out.println(list.get(i).getClass());
                System.out.print(list.get(i).getName()+" "+list.get(i).getNic()+" ");
            }
            System.out.println();
        }

       }



       public void remove()
       {
        serializeData ser = new serializeData();

        try {
            list = ser.deSerialize();
        } catch (Exception e) {
            System.out.println("No Data!");
        }

       }
      
}



class SerializeNoticeList
{
    ArrayList<notice> noticeList = new ArrayList<>();

    public void Serialize(ArrayList<notice> list) throws Exception
    {
        FileOutputStream fos = new FileOutputStream("Notices.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);

    }

    public ArrayList<notice> deSerialize() throws Exception
    {
        FileInputStream fis = new FileInputStream("Notices.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object o = ois.readObject();
        noticeList = (ArrayList<notice>) o;
        return noticeList;

    }
}


class notice implements Serializable
{
    

    private String name;
    private String subject;
    private String content;
    private String date;
    
    public notice(String name , String subject , String content , String date)
    {
        this.name = name;
        this.subject = subject;
        this.content = content;
        this.date = date;
    }


    public String getName()
    {
        return name;

    }

    public String getSubject()
    {
        return subject;
    }

    public String getContent()
    {
        return content;

    }

    public String getDate()
    {
        return date;

    }


}
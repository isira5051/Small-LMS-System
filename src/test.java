import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class test
{
   // public static void main(String[] args)  throws Exception
     {
        //studentMenu st = new studentMenu();
        //st.menu("Dave");
    }
    
}
 class subjectWater 
{
    
    // add students subjects
    public void menu() throws Exception
    {
        ArrayList<person> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<subject> subjectList = new ArrayList<>();
        serializeData ser = new serializeData();
        person person = null;
        Scanner input = new Scanner(System.in);
        try {
            list = ser.deSerialize();
        } catch (Exception e) {
            
           System.out.println("No Data!");
        }
  
        System.out.println("name  subject list");
        String name = input.nextLine();
        String subjects = input.nextLine();

        
        createSubjectList createSubjectList = new createSubjectList();
        list2 = createSubjectList.create(subjects);
        boolean validName = false;
        
        for(person p : list)
        {
            if(p.getName().equals(name))
            {
                person = p;
                validName = true;
            }
        }
        

        if(validName)
        {
            subject subject = new subject(person, list2);
           
            serializeSubjectList serSubject = new serializeSubjectList();
            
            try {
                subjectList = serSubject.deSerialize();
                boolean duplicate = false;

                if(subjectList.size() != 0)
                {
                  
                    for(subject sub : subjectList)
                    {
                        if(sub.geStudent().getName().equalsIgnoreCase(person.getName()))
                        {
                            duplicate = true;
                            // it has already a list
                         
                            ArrayList<String> tempSubjectList = new ArrayList<>();
                            tempSubjectList = sub.getList();
                            for(int i = 0 ; i < list2.size() ; i ++)
                            {
                                if(tempSubjectList.contains(list2.get(i)))
                                {
                                    System.out.println("subject already added -> " +list2.get(i));
                                }
                                
                                else
                                {
                                    tempSubjectList.add(list2.get(i));
                                }
                            }
                            sub.setSubjectList(tempSubjectList);

                        } 
                    }
                    
                    if(!duplicate)
                    {
                        subjectList.add(subject);
                    }
                }

                if(subjectList.size() == 0)
                    {
                        subjectList.add(subject);

                    }

            } catch (Exception e) {
                subjectList.add(subject);
                
            }
            
            serSubject.Serialize(subjectList);
        }

        else{
            System.out.println("Not Added -> "+name);
        }

        
    }
    

}



class subject implements Serializable
{
    private person s;
    private ArrayList<String> subjectList = new ArrayList<>();
    


    public subject(person getS , ArrayList<String> getSubjectList)
    {
        s = getS;
        subjectList = getSubjectList;
    }

    public person geStudent()
    {
        return s;
    }

    public ArrayList<String> getList()
    {
        return subjectList;
    }

    public void  setSubjectList(ArrayList<String> getTempSubjectList)
    {
        this.subjectList = getTempSubjectList;
    }
}

class createSubjectList
{
    ArrayList<String> subjectList = new ArrayList<>(); 
    int countSpace = 0;
    String subject = "";
    public ArrayList<String> create(String subjects)
    {
        subjects += " ";
        for(int i = 0; i < subjects.length() ; i ++)
        {   
        
            
            
           
            
            String s = String.valueOf(subjects.charAt(i));
            if(s != " " )
            {
                
                subject += s;
            }
            if(String.valueOf(subjects.charAt(i)).equals(" ") || i == subjects.length()- 1)
            {
                subjectList.add(subject);
                subject = "";
            }
            

            
        }

        return subjectList;

        
    }

   
}



class studentMenu
{
    
    ArrayList<subject> list;
    ArrayList<notice> noticeList;
    public void menu(String name) throws Exception
    {   
    
        Scanner input = new Scanner(System.in);
        
        while(true)
        {
            System.out.println();
            System.out.println();
            System.out.println("1. View Notice  2. View Subjects   4. Sign Out");
            int num = input.nextInt();
            boolean isValid = true;
            if(num == 1)
            {
                studentFunctions studentFunctions = new studentFunctions();
                list = studentFunctions.getSubjectList();
                noticeList = studentFunctions.getNoticetList();
                
                if(list == null || noticeList == null)
                {
                    isValid = false;
                }
                
            
                if(isValid)
                {
                     reshapeList reshapeList = new reshapeList();
                
                
                

                    for(subject subject : list)
                    {
                        if(subject.geStudent().getName().equalsIgnoreCase(name))
                        {
                        
                            ArrayList<String> subList = subject.getList(); 
                            subList = reshapeList.reshape(subList);
                            for(int i = 0 ; i < subList.size() ; i++)
                            {
                                for(int j = 0 ; j < noticeList.size() ; j++ )
                                {
                                
                                    if(noticeList.get(j).getSubject().equalsIgnoreCase(subList.get(i)))
                                    {
                                        System.out.println();
                                        System.out.println(noticeList.get(j).getDate());
                                        System.out.println();
                                        System.out.println(noticeList.get(j).getSubject()+"  -->  ");
                                        System.out.println();
                                        System.out.println("*****************************************************************************************************************************************************************************");
                                        System.out.println(noticeList.get(j).getContent());
                                        System.out.println("*****************************************************************************************************************************************************************************");
                                    }
                                }
                            }
                            
                            
                            
                        }
                    }
                }





            }
            else if(num == 2)
            {
                System.out.println();
                studentFunctions studentFunctions = new studentFunctions();
                list = studentFunctions.getSubjectList();
                for(subject subject : list)
                {
                    if(subject.geStudent().getName().equalsIgnoreCase(name))
                    {
                        ArrayList<String> subList = new ArrayList<>();
                        subList = subject.getList();
                        System.out.println("*****************************************************************************************************************************************************************************");
                        System.out.println("Current Registered Subjects");
                        System.out.println("*****************************************************************************************************************************************************************************");
                        System.out.println();
                        for(int i = 0 ; i < subList.size() ; i ++)
                        {
                            System.out.println(subList.get(i));
                        }
                        System.out.println();
                    }
            }

            

            }
            else if(num == 4)
            {
                break;
            }

                
                
        }

        // back to login
                System.out.println();
                System.out.println();
                System.out.println();
                loginClass loginClass = new loginClass();
                loginClass.login();

        

    }


    
}

class reshapeList
{
    ArrayList<String> finalSubjectList = new ArrayList<>();

    public ArrayList<String> reshape(ArrayList<String> list)
    {   
        
        for(String s : list)
        {
            String temp ="";
            for(int i = 0 ; i < s.length(); i++)
            {
                if(!String.valueOf(s.charAt(i)).equals(" "))
                {
                    temp += String.valueOf(s.charAt(i));
                }
            }

            finalSubjectList.add(temp);

        }
        
        return finalSubjectList;

    }

  
}


class studentFunctions
{
    ArrayList<subject> list;
    ArrayList<notice> noticeList;
    public ArrayList<subject> getSubjectList() throws Exception
    {
        serializeSubjectList serSubject = new serializeSubjectList();
        try {
            list = serSubject.deSerialize();
           
        } catch (Exception e) {
            System.out.println("No Data for Student");
            
        }

        return list;
    }

     public ArrayList<notice> getNoticetList() throws Exception
    {
        SerializeNoticeList ser = new SerializeNoticeList();
        try {
            noticeList = ser.deSerialize();
           
        } catch (Exception e) {
            System.out.println("No Notice for Student");
            
        }

        return noticeList;
    }

}



class serializeSubjectList
{
    private ArrayList<subject> List = new ArrayList<>();

    
    public void Serialize(ArrayList<subject> subjectList) throws Exception
    {
        FileOutputStream fos = new FileOutputStream("subjects.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(subjectList);

    }

    public ArrayList<subject> deSerialize() throws Exception
    {
        FileInputStream fis = new FileInputStream("subjects.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object o = ois.readObject();
        List = (ArrayList<subject>) o;
        return List;

    }
}


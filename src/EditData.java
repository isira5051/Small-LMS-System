import java.util.ArrayList;
import java.util.Scanner;

public class EditData 
{
    ArrayList<person> list = new ArrayList<>();
    ArrayList<processedList> studentList = new ArrayList<>();
    ArrayList<processedList> teacherList = new ArrayList<>();
    public void AdminData()
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
            int index = 0;
            
            for(person person : list)
            {
                processedList processedList = new processedList(person, index);
                if(person instanceof student)
                {
            
                    studentList.add(processedList);

                }
                else if(person instanceof teacher)
                {
                    teacherList.add(processedList);
                }

                index ++;
            }
        }


        System.out.println("*****************************************************************************************************************************************************************************");
        System.out.println("                                                   Student  Data");
        System.out.println("*****************************************************************************************************************************************************************************");
        System.out.println();
        System.out.println("index               name                 nic                 age            ");
        System.out.println("*****************************************************************************************************************************************************************************");
        removedataList removedataList = new removedataList();

        removedataList.print(studentList);
        System.out.println();System.out.println("*****************************************************************************************************************************************************************************");
        System.out.println("                                                   Teacher  Data");
        System.out.println("*****************************************************************************************************************************************************************************");
        System.out.println();
        System.out.println("index               name                nic                 age                 salary");
        System.out.println("*****************************************************************************************************************************************************************************");
        removedataList.print(teacherList);
        




    }
    
}


class removedataList
{
    ArrayList<person> list = new ArrayList<>();
    ArrayList<subject> list2 = new ArrayList<>();
    public void print(ArrayList<processedList> list)
    {
        for(processedList processedList : list)
        {
            if(processedList.getPerson() instanceof student)
            {
                System.out.println("   "+processedList.getIndex()+"             "+processedList.getPerson().getName()+"             "+processedList.getPerson().getNic()+"             "+processedList.getPerson().getAge());
            }

            else if(processedList.getPerson() instanceof teacher)
            {
System.out.println("   "+processedList.getIndex()+"             "+processedList.getPerson().getName()+"             "+processedList.getPerson().getNic()+"             "+processedList.getPerson().getAge()+"             "+processedList.getPerson().getSalary());
            }
        }
    }

    public void printSubjectList()
    {
        serializeSubjectList ser = new serializeSubjectList();

        try {
            
            list2 = ser.deSerialize();
        } catch (Exception e) {

            System.out.println("No Subject Data");
            
        }
        
 
        for(subject subject : list2)
        {
            System.out.println();
            System.out.println("*****************************************************************************************************************************************************************************");
            System.out.println(subject.geStudent().getName() + "  ->  ");
            System.out.println("*****************************************************************************************************************************************************************************");
            for(int i = 0 ; i < subject.getList().size() ; i ++)
            {
                System.out.print(subject.getList().get(i));
            }
            System.out.println();
            System.out.println("*****************************************************************************************************************************************************************************");
            System.out.println();
            
        }





    }


    public void removePersonList() throws Exception
    {
        boolean proceed = true;
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("enter index -> ");
        int index = input.nextInt();
        serializeData ser = new serializeData();

         try {
            list = ser.deSerialize();
        } catch (Exception e) {
            System.out.println("No Data!");
            proceed = false;
        }

        if(proceed)
        {
            list.remove(index);
        }

        ser.serialize(list);


    }


    public void removeSubjectList() throws Exception
    {
        Scanner input = new Scanner(System.in);
        System.out.println();
        serializeSubjectList ser = new serializeSubjectList();
        boolean proceed = true;
         try {
            list2 = ser.deSerialize();
        } catch (Exception e) {
            System.out.println("No Data!");
            proceed = false;
        }
      
        
        if(proceed && list2.size() > 0)
        {
            System.out.println("enter name ");
            String name = input.nextLine();
            
            int index = 0;
            boolean run = false;
            boolean valid = false;
            for(subject subject : list2)
            {
                if(subject.geStudent().getName().equals(name))
                {   
                    valid = true;
                    break;

                }
                index ++;
            }

        
            if(valid)
            {
                list2.remove(index);
            }
            

            ser.Serialize(list2);
        }

    }



}

class processedList
{
    private person person;
    private int index;

    public processedList(person person , int index)
    {
        this.person  = person;
        this.index = index;

    }

    public person getPerson()
    {
        return this.person;
    }

    public int getIndex()
    {
        return index;
    }
}
package lru;


import java.util.*;

public class LRU {
    Scanner scanner = new Scanner(System.in);
    int no_frames,counter=0,pagefaults=0;
    int[] wait;
    int[] frame_value;
    String[] str;
    LRU(int no)
    {
        no_frames = no;
        wait = new int[no_frames];
        str = new String[no_frames];
        frame_value = new int[no_frames];
        init();
        int addval=0;
        System.out.print("Enter no to add: ");
        while (addval!=-1)
        {
            addval=scanner.nextInt();
            if(addval!=-1) {
                add(addval);
            }
        }
        for(int i=0;i<no_frames;i++)
        {
            System.out.println("Values in frame "+i+" :"+str[i]);
        }
        System.out.println("Page Faults: "+pagefaults);
    }
    void init()
    {
        for(int i=0;i<no_frames;i++){
            wait[i]=0;
            frame_value[i]=-1;
            str[i]="";
        }
    }
    boolean ifExist(int val)
    {
        for(int i=0;i<no_frames;i++)
        {
            if(frame_value[i]==val){
                wait[i]=-1;
                return true;
            }

        }
        return false;
    }
    int findLRU()
    {
        int max_value=Integer.MIN_VALUE,max_index=0;

        for(int i=0;i<no_frames;i++)
        {
            if(wait[i]>max_value)
            {
                max_value=wait[i];
                max_index=i;
            }
        }
        return max_index;
    }
    void incWait()
    {
        for (int i=0;i<no_frames;i++)
            wait[i]++;
    }
    void add(int no)
    {
        if (ifExist(no))
        {
            System.out.println("\nAlready in memory");
        }
     
        else {
            int i;
            i=findLRU();
            wait[i]=-1;
            frame_value[i]=no;
            pagefaults++;
            System.out.println("Added to page : "+i+" ");
            str[i]=str[i]+" "+no;
        }
        incWait();
    }
}

public class Source {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args)
    {
        System.out.print("\nEnter no. of Page frames : ");
        int no;
        no = scanner.nextInt();
        LRU lru = new LRU(no);
    }
}

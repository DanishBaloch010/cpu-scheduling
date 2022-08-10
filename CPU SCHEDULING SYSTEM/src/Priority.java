import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Priority extends CPUScheduler {
   
    // abstract method overridden 
    @Override
    public void process()
    {
        // Sorting incoming processes on the basis of arrival time //
        Collections.sort(this.getRows(), (Object o1, Object o2) -> {
             // If arrival is same, we will sort based on priroity //
            if (((Row) o1).getArrivalTime() == ((Row) o2).getArrivalTime())
            {
                if (((Row) o1).getPriority() == ((Row) o2).getPriority())
                {
                    return 0;
                }
                else if (((Row) o1).getPriority() < ((Row) o2).getPriority())
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
             }
            
            else if (((Row) o1).getArrivalTime() < ((Row) o2).getArrivalTime())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        });
        
        /*  Making a copy (with objects) of original rows.
            This is because we will need to change the arrangement of rows 
            according to our algorithm
        */
        List<Row> rows;
        rows = Utility.deepCopy(this.getRows());
        
        /* Begin maintaining current time */
        
        int time = rows.get(0).getArrivalTime();
        
        // jub tak original rows khali nahi hojati.
        while(!rows.isEmpty())
        {
             List<Row> availableRows = new ArrayList();
             
             // For each loop: Used to iterate through array elements and lists
            for (Row row : rows)
            {
                if (row.getArrivalTime() <= time)
                {
                    availableRows.add(row);
                }
            }
            
            /*
                Sorting on the basis of priority after the program has arrived 
                and is currently in avialble rows. 
            */
             Collections.sort(availableRows, (Object o1, Object o2) -> {
                if (((Row) o1).getPriority() == ((Row) o2).getPriority())
                {
                    return 0;
                }
                else if (((Row) o1).getPriority() < ((Row) o2).getPriority())
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            });
             
             // jitne process bach gaye hai ab unme se. 
             // ab yaha pe sub se pehle top priority wala process hai mere pass.
             Row row = availableRows.get(0);
             
             this.getTimeline().add(new Event(row.getProcessName(),time, time + row.getBurstTime()));
             
             // Maintaing current time with the help of previous process' burst time
             // updating the time
             time += row.getBurstTime();
             
             for(int i = 0; i < rows.size(); i++)
             {
                 /*  Once process has executed, remove from rows. 
                     but keep in row for gantt chart, this is why we used deep copy
                 */
                if (rows.get(i).getProcessName().equals(row.getProcessName()))
                {
                    rows.remove(i);
                    break;
                }
             }
        }
        
        // once all processes are executed than we are calculating wt and tat.
        // for each process
        for (Row row: this.getRows())
        {
            row.setWaitingTime(this.getEvent(row).getStartTime() - row.getArrivalTime());
            row.setTurnaroundTime(row.getWaitingTime() + row.getBurstTime());
        }
    }
}

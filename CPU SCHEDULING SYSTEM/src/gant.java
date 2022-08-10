
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;


public class gant
{
    

    CustomPanel chartPanel;
    public gant()
    {
        chartPanel = new CustomPanel();
        chartPanel.setBackground(Color.WHITE);
    }
    
    
    
    public static void main(String[] args)
    {
        
    }
    
    class CustomPanel extends JPanel
    {   
        private List<Event> timeline;
        
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
            if (timeline != null)
            {
//                int width = 30;
                
                for (int i = 0; i < timeline.size(); i++)
                {
                    Event event = timeline.get(i);
                    int x = 30 * (i + 1);
                    int y = 20;
                    
                    g.drawRect(x, y, 30, 30);
                    g.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    g.drawString(event.getProcessName(), x + 10, y + 20);
                    g.setFont(new Font("Segoe UI", Font.PLAIN, 11));
                    g.drawString(Integer.toString(event.getStartTime()), x - 5, y + 45);
                    
                    if (i == timeline.size() - 1)
                    {
                        g.drawString(Integer.toString(event.getFinishTime()), x + 27, y + 45);
                    }
                }
            }
        }
        
        public void setTimeline(List<Event> timeline)
        {
            this.timeline = timeline;
            repaint();
        }
    }
}

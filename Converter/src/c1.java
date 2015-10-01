import java.io.*;

/**
 * Created by steppers on 01/10/15.
 */
public class c1 {

    public static void main(String[] args)
    {
        FileInputStream in;
        BufferedWriter out;

        if(args.length != 1)
        {
            System.out.println("Usage: <filename>");
            System.exit(-1);
        }

        try {
            in = new FileInputStream(args[0]);
            out = new BufferedWriter(new FileWriter(args[0].substring(0, args[0].lastIndexOf('.')) + ".lgs"));
            out.flush();
            out.append("v2.0 raw\n");

            char[][] data = new char[4096][4096];

            int c;
            int x = 0, y = 0;
            while((c = in.read()) != -1)
            {
                if((char)c == '\n')
                {
                    y++;
                    x = 0;
                    continue;
                }
                data[x++][y] = (char)c;
            }

            int zeros = 0;
            for(int j = 0; j <= y; j++)
            {
                for(int i = 0; i < 4096; i++)
                {
                    if(data[i][j] == '\0')
                    {
                        zeros++;
                    }else{
                        if(zeros > 0)
                        {
                            out.append(String.valueOf(zeros)+"*0 ");
                            zeros = 0;
                        }
                        out.append(Integer.toHexString(data[i][j]) + " ");
                    }
                }
                out.append("\n");
            }

            in.close();
            out.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please enter a valid filename.");
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}

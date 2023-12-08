package screens;

public class Utils {

    /**
     * Clears the screen.
     */
    public static void clearScreen () {
        /*
        System.out.print("\033[H\033[2J");
        System.out.flush();
        */
        /*
        try
        {
        final String os = System.getProperty("os.name");
        if (os.contains("Windows"))
        {
        Runtime.getRuntime().exec("cls");
        }
        }
        catch (final Exception e)
        {
        e.printStackTrace();
        }
        */

        /*
        try
        {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows"))
            {
                //Runtime.getRuntime().exec("cls");
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else
            {
                //Runtime.getRuntime().exec("clear");
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }*/

        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

}

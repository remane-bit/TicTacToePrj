package package1;

import javax.swing.*;

public class SuperTicTacToe {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Super Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //phil pil = new startParametersPanel();
        JTextField rowsField = new JTextField(2);
        JTextField colsField = new JTextField(2);
        JTextField inaRowField = new JTextField(2);
        JTextField whosFirstField = new JTextField(1);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Number of Rows:"));
        myPanel.add(rowsField);

        myPanel.add(new JLabel("Number of Columns:"));
        myPanel.add(colsField);

        myPanel.add(new JLabel("How Many Need to be in a Row to Win?:"));
        myPanel.add(inaRowField);

        myPanel.add(new JLabel("Who Goes First (Please enter X of O):"));
        myPanel.add(whosFirstField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);


        SuperTicTacToePanel panel = new SuperTicTacToePanel();
        frame.getContentPane().add(panel);

        frame.setSize(600, 500);
        frame.setVisible(true);
    }

}

package ua.nure.kulychenko.practice6.part6;

import java.io.FileNotFoundException;

public class Part6 {

    private static final int FREQUENCY = 1;
    private static final int LENGTH = 2;

    public static void main(String[] args) throws FileNotFoundException {
        String path = "";
        int taskType = -1;
        int pathFlag = -1;
        int taskFlag = -1;
        for (int i = 0; i < args.length; i++) {
            if (i == pathFlag) {
                path = args[i];
            } else if (i == taskFlag) {
                if ("frequency".equals(args[i])) {
                    taskType = 1;
                } else if ("length".equals(args[i])){
                    taskType = 2;
                } else {
                    taskType = 3;
                }
            }
            if ("-i".equals(args[i]) || "--input".equals(args[i])) {
                pathFlag = i + 1;
            } else if ("-t".equals(args[i]) || "--task".equals(args[i])) {
                taskFlag = i + 1;
            }
        }
        String[] newArgs = new String[] {path};
        switch (taskType) {
            case FREQUENCY:
                Part61.main(newArgs);
                break;
            case LENGTH:
                Part62.main(newArgs);
                break;
            default:
                Part63.main(newArgs);
        }
    }
}

package taskpool;

import java.util.ArrayList;
import java.util.List;

public class Task {


        /**

         * A list of shell commands.

         */

        private List<String> commands = new ArrayList<>();

        /**

         * true if stdout/stderr of commands needs to be recorded.

         */

        private boolean captureOutput;

        /**

         * true if the task cannot be run concurrently with other tasks.

         */

        private boolean exclusive;

        public List<String> getCommands() {
            return commands;
        }

        public boolean getCaptureOutput() {
            return captureOutput;
        }

        public void setCaptureOutput(boolean captureOutput) {

            this.captureOutput = captureOutput;
        }


        public boolean isExclusive() {
            return exclusive;
        }

        public void setExclusive(boolean exclusive) {
            this.exclusive = exclusive;
        }



}
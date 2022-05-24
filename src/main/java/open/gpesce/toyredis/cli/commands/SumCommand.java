package open.gpesce.toyredis.cli.commands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class SumCommand {
    @ShellMethod("función de suma")
    public int add(int param1,int param2, @ShellOption(defaultValue="0")int param3) {
        return param1+param2+param3;
    }
}

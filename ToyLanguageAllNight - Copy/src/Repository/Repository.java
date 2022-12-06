package Repository;

import Model.ADT.List.MyIList;
import Model.ADT.List.MyList;
import Model.ADT.Stack.MyIStack;
import Model.PrgState.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Repository implements IRepository{
    private MyIList<PrgState> prgState;
    int length;
    String logFilePath;

    public Repository(String logFilePath){
        prgState = new MyList<PrgState>();
        length = 0;
        this.logFilePath = logFilePath;
    }

    public Repository(PrgState prg, String logFilePath){
        prgState = new MyList<PrgState>();
        length = 0;
        this.addPrg(prg);
        this.logFilePath = logFilePath;
    }

    @Override
    public void addPrg(PrgState p) {
        prgState.add(p);
        length++;
    }

    @Override
    public PrgState getCrtPrg() {
        return prgState.get(length - 1);
    }

    public void logPrgStateExec() throws Exception{
        PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.append(this.getCrtPrg().toString()).append(" ---- \n\n");
        logFile.close();
    }
}

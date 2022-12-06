package Controller;

import Model.ADT.Stack.MyIStack;
import Model.PrgState.PrgState;
import Model.Statement.IStmt;
import Model.Value.RefValue;
import Model.Value.Value;
import Repository.IRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    private IRepository repo;

    public Controller(IRepository repo) {
        this.repo = repo;
    }

    public PrgState oneStep(PrgState state) throws Exception {
        MyIStack<IStmt> stk = state.getExeStack();
        if (stk.isEmpty()) throw new Exception("prgstate stack is empty");
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }


    public void allStep() throws Exception {
        PrgState prg = repo.getCrtPrg(); // repo is the controller field of type MyRepoInterface
        // here you can display the prg state
        System.out.println(prg.toString());
        this.repo.logPrgStateExec();
        while (!prg.getExeStack().isEmpty()) {
            oneStep(prg);
            System.out.println(prg);
            this.repo.logPrgStateExec();
            prg.getHeap().setContent((HashMap<Integer, Value>) unsafeGarbageCollector(
                    getAddrFromSymTable(prg.getSymTable().getContent().values()),
                    prg.getHeap().getContent()));
            //here you can display the prg state
        }
    }

    public void addPrg(PrgState prg){
        this.repo.addPrg(prg);
    }


    Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer,Value> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                        .collect(Collectors.toMap(HashMap.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());}
}

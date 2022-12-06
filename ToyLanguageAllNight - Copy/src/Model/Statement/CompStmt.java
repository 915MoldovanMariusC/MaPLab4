package Model.Statement;

import Model.ADT.Stack.MyIStack;
import Model.PrgState.PrgState;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt snd;

    public CompStmt(IStmt first,IStmt second){
        this.first = first;
        this.snd = second;
    }

    public String toString() {
        return "("+first.toString() + ";" + snd.toString()+")";
    }
    public PrgState execute(PrgState state)  throws Exception{
        MyIStack<IStmt> stk=state.getExeStack();
        stk.push(snd);
        stk.push(first);
        return state;    }
}
package Model.Statement;

import Model.PrgState.PrgState;

public interface IStmt {
    PrgState execute(PrgState state) throws Exception;       //which is the execution method for a statement.         }
}

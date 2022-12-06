package Repository;

import Model.ADT.List.MyIList;
import Model.PrgState.PrgState;

public interface IRepository {
    public void addPrg(PrgState prgState);
    public PrgState getCrtPrg();

    public void logPrgStateExec() throws Exception;
}

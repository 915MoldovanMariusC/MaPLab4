package Model.Expression;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Value.Value;

public class ValueExp implements Exp{
    Value e;
    //....
    public ValueExp(Value e){
        this.e = e;
    }
    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> hp)  throws Exception{return e;}
    //....
}
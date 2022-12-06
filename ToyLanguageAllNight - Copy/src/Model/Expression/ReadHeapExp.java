package Model.Expression;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Value.RefValue;
import Model.Value.Value;

public class ReadHeapExp implements Exp{


    Exp exp;

    public ReadHeapExp(Exp exp){ this.exp = exp;}

    public String toString(){return "Read(" + exp.toString() + ")";}

    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> hp) throws Exception {
        Value result = exp.eval(tbl, hp);
        if(result instanceof RefValue){
            Integer address = ((RefValue) result).getAddress();
            Value value = hp.lookup(address);
            if(value == null){
                throw new RuntimeException("Invalid address");
            }
            return value;
        } else throw new RuntimeException("Not a ref value");
    }
}

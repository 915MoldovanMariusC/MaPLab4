package Model.Expression;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

public class RelationExp implements Exp {

    private Exp exp1;
    private Exp exp2;
    private int op;

    public RelationExp(String op, Exp exp1, Exp exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        switch(op){
            case "<" -> this.op = 1;
            case "<=" -> this.op = 2;
            case "==" -> this.op = 3;
            case "!=" -> this.op = 4;
            case ">" -> this.op = 5;
            case ">=" -> this.op = 6;
        }
    }

    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> hp) throws Exception {
        Value v1, v2;
        v1 = exp1.eval(tbl, hp);
        if(v1.getType().equals(new IntType())){
            v2 = exp2.eval(tbl, hp);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(op == 1) return new BoolValue(n1 < n2);
                if(op == 2) return new BoolValue(n1 <= n2);
                if(op == 3) return new BoolValue(n1 == n2);
                if(op == 4) return new BoolValue(n1 != n2);
                if(op == 5) return new BoolValue(n1 > n2);
                if(op == 6) return new BoolValue(n1 >= n2);
            } else{
                throw new Exception("Second operand is not an integer");
            }
        } else {
            throw new Exception("First operand is not an integer");
        }

        return null;
    }
}

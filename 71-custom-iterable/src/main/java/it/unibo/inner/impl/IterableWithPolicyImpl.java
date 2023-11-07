package it.unibo.inner.impl;

import it.unibo.inner.api.Predicate;
import java.util.NoSuchElementException;
import java.util.Arrays;

import java.util.Iterator;
import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private  T[] array;
    Predicate<T> filter;

    public IterableWithPolicyImpl (T[] array){
        this(array, t -> true);
        
    }


    public IterableWithPolicyImpl (T[] array, Predicate<T> filter){
        this.array = array;
        this.filter=filter;
    }

    public void setIterationPolicy(Predicate<T> filter){
            this.filter=filter;
    }


    @Override
    public Iterator<T> iterator(){
        return this.new InnerIterator();
    }

    public String toString() {
        return Arrays.toString(array);
    }

private class InnerIterator implements Iterator<T>{

    private int index=0;
    private int size;
    
    private T[] currentElem;

     public InnerIterator(){
        this.currentElem = IterableWithPolicyImpl.this.array;
        this.size = array.length;
     }
     
     @Override
     public T next(){
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return this.currentElem[index++];
     }

     @Override
     public boolean hasNext(){
        while(index < size && !filter.test(currentElem[index])){
            index++;
        }
         return this.index < this.size;
     }

     public void remove(){
        throw new UnsupportedOperationException("Remove operation is not supported");
     } 

   
  }    
  
 
}
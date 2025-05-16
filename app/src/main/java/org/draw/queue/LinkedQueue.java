package org.draw.queue;

import org.draw.queue.QueueInterface;

import java.util.Iterator;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements QueueInterface<T>, Iterable<T>
{
  private static class LNode<T>
  {
    private T data;
    private LNode<T> next;

    public LNode(T data)
    {
      this.data = data;
    }
  }

  private LNode<T> head;
  private LNode<T> tail;

  @Override
  public void push_back(T data)
  {
    LNode<T> node = new LNode<>(data);
    if ( tail == null ) 
    {
      head = tail = node;
    }
    else
    {
      tail.next = node;
      tail = node;
    }
  }

  @Override
  public T pop()
  {
    if ( head == null ) throw new EmptyStackException();
    T data = head.data;
    head = head.next;
    if ( head == null ) tail = null;
    return data;
  }

  @Override
  public T peek()
  {
    if ( head == null ) throw new EmptyStackException();
    return head.data;
  }

  @Override
  public boolean isEmpty()
  {
    return head == null;
  }

  @Override
  public Iterator<T> iterator()
  {
    return new Iterator<T>()
    {
      private LNode<T> current = head;
      
      public boolean hasNext()
      {
        return current != null;
      }
      
      public T next()
      {
        if (!hasNext()) throw new NoSuchElementException();
        T data = current.data;
        current = current.next;
        return data;
      }

    };
  }

}

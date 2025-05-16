package org.draw.queue;

public interface QueueInterface<T>
{
  void push_back(T data);
  T pop();
  T peek();
  boolean isEmpty();
}

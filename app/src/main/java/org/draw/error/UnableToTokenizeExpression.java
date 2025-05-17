package org.draw.error;

import java.lang.RuntimeException;

public class UnableToTokenizeExpression extends RuntimeException
{
  public UnableToTokenizeExpression(String detail)
  {
    super(detail);
  }
}

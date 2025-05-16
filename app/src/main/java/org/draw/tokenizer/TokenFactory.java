package org.draw.tokenizer;

import org.draw.tokenizer.Token;
import org.draw.tokenizer.TokenType;

import org.draw.queue.LinkedQueue;

public class TokenFactory
{
  private LinkedQueue<Token> tokens;

  public TokenFactory(LinkedQueue<Token> tokens)
  {
    this.tokens = tokens;
  }

  public void tokenize(String line)
  {
    if ( line.startsWith("[") && line.endsWith("]") )
    {
      String sectionName = line.substring(1, line.length() - 1).trim();
      if ( sectionName.equals("Drawing") ) this.tokens.push_back(new Token(TokenType.MASTER_SECTION, "Drawing"));
      else this.tokens.push_back(new Token(TokenType.SECTION, sectionName));
    }
  }
}

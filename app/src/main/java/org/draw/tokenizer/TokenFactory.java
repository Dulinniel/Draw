package org.draw.tokenizer;

import org.draw.tokenizer.Token;
import org.draw.tokenizer.TokenType;
import org.draw.error.UnableToTokenizeExpression;

import org.draw.queue.LinkedQueue;

public class TokenFactory
{
  private final LinkedQueue<Token> tokens;

  public TokenFactory(LinkedQueue<Token> tokens)
  {
    this.tokens = tokens;
  }

  private boolean isSection(String lexem)
  {
    return lexem.startsWith("[") && lexem.endsWith("]");
  }

  private boolean isComment(String lexem)
  {
    return lexem.contains("#");
  }

  private boolean isProperty(String lexem)
  {
    return lexem.contains("=");
  }

  private boolean isInvalidKeyValuePair(String[] lexem)
  {
    return lexem.length < 2 || lexem[0].trim().isEmpty();
  }

  public void tokenize(String line)
  {
    if ( this.isComment(line) ) 
    {
      String comment = line.substring(line.indexOf("#"), line.length());
      this.tokens.push_back(new Token(TokenType.COMMENT, comment.trim()));
      if ( !line.startsWith("#") ) line = line.substring(0, line.indexOf("#")).trim();
    }
    else if ( this.isSection(line) )
    {
      String sectionName = line.substring(1, line.length() - 1).trim();
      if ( sectionName.equals("Drawing") ) this.tokens.push_back(new Token(TokenType.MASTER_SECTION, "Drawing"));
      else this.tokens.push_back(new Token(TokenType.SECTION, sectionName));
    } 
    else if ( this.isProperty(line) )
    {
      String[] properties = line.split("=", 2);
      if ( this.isInvalidKeyValuePair(properties) ) throw new UnableToTokenizeExpression("Invalid key/value pair: " + line);
      String left_expression = properties[0];
      String right_expression = properties[1];
      this.tokens.push_back(new Token(TokenType.KEY, left_expression));
      this.tokens.push_back(new Token(TokenType.VALUE, right_expression));
    } else
    {
      throw new UnableToTokenizeExpression("The lexem Can't be tokenized");
    }

  }
}

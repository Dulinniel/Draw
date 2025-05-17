package org.draw;

import org.draw.file.FileManager;
import org.draw.tokenizer.TokenFactory;
import org.draw.tokenizer.Token;
import org.draw.queue.LinkedQueue;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main 
{
  public static void main(String[] args) throws FileNotFoundException, IOException 
  {
    FileManager file = new FileManager("file.draw");
    LinkedQueue<Token> tokens = new LinkedQueue();
    TokenFactory factory = new TokenFactory(tokens);
    String line = file.NextLine();
    while ( !line.equals(file.getCRLF()) )
    {
      factory.tokenize(line);
      line = file.NextLine();
    }
    file.Close();
    for ( Token token : tokens )
    {
      System.out.println(token);
    }
  }
}

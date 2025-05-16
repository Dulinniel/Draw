package org.draw.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FileManager
{
  private String fileName;
  private BufferedReader buffer;
  private FileReader reader;
  private final String extension = ".draw"; 
  private final String CRLF = "\n\r";

  public String getCRLF()
  {
    return this.CRLF;
  }

  public FileManager(String fileName) throws FileNotFoundException, IOException
  {
    this.fileName = fileName;
    String pwd = System.getProperty("user.dir");
    File file = new File(pwd, fileName);

    if ( !file.exists() || !IsValidFile() ) throw new IOException("Not a valid .draw file");

    this.reader = new FileReader(file);
    this.buffer = new BufferedReader(reader);
  }

  private Boolean IsValidFile()
  {
    return this.fileName.endsWith(extension);
  }

  public String NextLine()
  {
    String line = null;
    try 
    { 
      line = buffer.readLine();
    } catch ( IOException exception )
    {
      exception.printStackTrace();
    }
    return line != null ? line : this.CRLF;
  }
 
  // Java really lacks Destructors
  public void Close() throws IOException
  {
    if ( this.buffer != null ) this.buffer.close();
  }

}

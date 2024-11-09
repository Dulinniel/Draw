package org.Draw.FileManager;

public class FileManager
{
  private String fileName;
  private static final String extension = ".draw"; 

  public FileManager(Srting file)
  {
    this.fileName = file;
  }

  protected Boolean CheckExtension()
  {
    return this.fileName.endsWith(extension);
  }

}

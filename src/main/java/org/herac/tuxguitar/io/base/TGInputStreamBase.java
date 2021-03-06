package org.herac.tuxguitar.io.base;

import java.io.IOException;
import java.io.InputStream;

import org.herac.tuxguitar.song.models.TGSong;

public interface TGInputStreamBase {

  public TGFileFormat getFileFormat();

  public void init(InputStream stream);

  public boolean isSupportedVersion();

  public TGSong readSong() throws TGFileFormatException, IOException;
}

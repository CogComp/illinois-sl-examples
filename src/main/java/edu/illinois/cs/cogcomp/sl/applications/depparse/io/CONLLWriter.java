package edu.illinois.cs.cogcomp.sl.applications.depparse.io;

import java.io.IOException;
import java.text.DecimalFormat;

import edu.illinois.cs.cogcomp.sl.applications.depparse.features.DependencyInstance;

/**
 * A writer to create files in CONLL format.
 * 
 * <p>
 * Created: Sat Nov 10 15:25:10 2001
 * </p>
 * 
 * @author Jason Baldridge
 * @version $Id: CONLLWriter.java 137 2013-09-10 09:33:47Z wyldfire $
 * @see mstparser.io.DependencyWriter
 */
public class CONLLWriter extends DependencyWriter {

  public CONLLWriter(boolean labeled) {
    this.labeled = labeled;
  }

  @Override
  public void write(DependencyInstance instance) throws IOException {
    DecimalFormat df = null;
    if (instance.confidenceScores != null) {
      df = new DecimalFormat();
      df.setMaximumFractionDigits(3);
    }
    for (int i = 0; i < instance.length(); i++) {
      writer.write(Integer.toString(i + 1));
      writer.write('\t');
      writer.write(instance.forms[i]);
      writer.write('\t');
      writer.write(instance.forms[i]);
      writer.write('\t');
      // writer.write(instance.cpostags[i]); writer.write('\t');
      writer.write(instance.postags[i]);
      writer.write('\t');
      writer.write(instance.postags[i]);
      writer.write('\t');
      writer.write("-");
      writer.write('\t');
      writer.write(Integer.toString(instance.heads[i]));
      writer.write('\t');
      writer.write(instance.deprels[i]);
      writer.write('\t');
      writer.write("-\t-");
      if (instance.confidenceScores != null) {
        writer.write('\t');
        writer.write(df.format(instance.confidenceScores[i]));
      }
      writer.newLine();
    }
    writer.newLine();

  }

}

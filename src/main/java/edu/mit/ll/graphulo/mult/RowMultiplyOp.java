package edu.mit.ll.graphulo.mult;

import edu.mit.ll.graphulo.skvi.TwoTableIterator;
import org.apache.accumulo.core.data.Key;
import org.apache.accumulo.core.data.Value;
import org.apache.accumulo.core.iterators.IteratorEnvironment;
import org.apache.accumulo.core.iterators.SortedKeyValueIterator;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Called on every two colliding rows in TwoTableIterator,
 * that is, every two rows in table A and B that match in row.
 */
public interface RowMultiplyOp {

  /**
   * Initializes the row multiply object.
   * Options are passed from <tt>rowMultiplyOp.opt.OPTION_NAME</tt> in the options for {@link TwoTableIterator}.
   *
   * @param options
   *          <tt>Map</tt> map of string option names to option values.
   * @param env
   *          <tt>IteratorEnvironment</tt> environment in which iterator is being run.
   * @throws IOException
   *           unused.
   * @exception IllegalArgumentException
   *              if there are problems with the options.
   */
  void init(Map<String,String> options, IteratorEnvironment env) throws IOException;

  /**
   *
   *
   * Post-condition: the two SKVIs should be seeked to the beginning of the next row.
   *
   * @param skviA Table A SKVI with {@link SortedKeyValueIterator#hasTop()} == true and {@link SortedKeyValueIterator#getTopKey()} matching skviB's Row.
   * @param skviB Table B SKVI with {@link SortedKeyValueIterator#hasTop()} == true and {@link SortedKeyValueIterator#getTopKey()} matching skviA's Row.
   * @return An iterator over entries generated by the multiplication of the two aligned rows.
   */
  Iterator<Map.Entry<Key,Value>> multiplyRow(SortedKeyValueIterator<Key,Value> skviA,
                                             SortedKeyValueIterator<Key,Value> skviB) throws IOException;

}
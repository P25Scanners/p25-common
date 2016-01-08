/*
 * Copyright (C) 2015 An Honest Effort LLC, coping.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.anhonesteffort.p25.protocol.frame;

import org.anhonesteffort.dsp.Copyable;
import org.anhonesteffort.dsp.Sink;
import org.anhonesteffort.p25.protocol.DiBit;
import org.anhonesteffort.p25.protocol.DiBitByteBufferSink;

import java.nio.ByteBuffer;

public class DataUnit implements Copyable<DataUnit>, Sink<DiBit> {

  protected final Nid                 nid;
  protected final DiBitByteBufferSink sink;

  protected DataUnit(Nid nid, DiBitByteBufferSink sink) {
    this.nid  = nid;
    this.sink = sink;
  }

  public DataUnit(Nid nid) {
    this(nid, new DiBitByteBufferSink(nid.getDuid().getBitLength()));
  }

  public Nid getNid() {
    return nid;
  }

  public boolean isFull() {
    return sink.isFull();
  }

  public boolean isIntact() {
    return false;
  }

  public ByteBuffer getBytes() {
    return sink.getBytes();
  }

  @Override
  public void consume(DiBit element) {
    sink.consume(element);
  }

  @Override
  public DataUnit copy() {
    return new DataUnit(nid, sink.copy());
  }

}
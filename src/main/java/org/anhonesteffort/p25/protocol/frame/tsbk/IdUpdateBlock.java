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

package org.anhonesteffort.p25.protocol.frame.tsbk;

public abstract class IdUpdateBlock extends SingleTrunkSignalBlock {

  protected final int  id;
  protected final int  channelSpacing;
  protected final long baseFreq;

  public IdUpdateBlock(int[] bytes12) {
    super(bytes12);

    id             = bytes12[2] >> 4;
    channelSpacing = bytes12[5];
    baseFreq       = (bytes12[6] << 24) + (bytes12[7] << 16) + (bytes12[8] << 8) + bytes12[9];
  }

  public int getId() {
    return id;
  }

  public abstract int getBandwidth();

  public abstract long getTransmitOffset();

  public int getChannelSpacing() {
    return channelSpacing * 125;
  }

  public long getBaseFreq() {
    return baseFreq * 5;
  }

  @Override
  public String toString() {
    return super.toString() + ", " +
        "id: "       + id             + ", " +
        "chnSpace: " + channelSpacing + ", " +
        "baseFreq: " + baseFreq       + ", " +
        "bw: "       + getBandwidth() + ", " +
        "txOff: "    + getTransmitOffset();
  }

}

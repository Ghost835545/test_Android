/*
    Copyright (C) 2014  sarin

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.ivan.loginapp.util.striBog;

/**
 *
 * @author sarin
 */
public final class Stribog512 extends Stribog{
    private static final int[] IV = new int[64];

    @Override
    protected byte[] engineDigest() {
        return getDigest(IV);
    }
    
}

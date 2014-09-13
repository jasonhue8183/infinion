/**
 * This file is part of mycollab-core.
 *
 * mycollab-core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-core.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.my.tdex.dnb.sccb2.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for processing class meta data.
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ClassUtils {
	/**
	 * Check whether object <code>o</code> is one of instance in class list
	 * <code>classes</code>
	 * 
	 * @param o
	 * @param classes
	 * @return
	 */
	public static boolean instanceOf(Object o, Class<?>... classes) {
		for (Class<?> cls : classes) {
			if (cls.isInstance(o)) {
				return true;
			}
		}
		return false;
	}

	public static Class<?> getInterfaceInstanceOf(Class cls, Class superCls) {
		Class[] interfaces = cls.getInterfaces();
		for (Class inter : interfaces) {
			if (superCls.isAssignableFrom(inter)) {
				return inter;
			}
		}

		return null;
	}

	/**
	 * Get all fields of class <code>type</code> includes its super classes
	 * 
	 * @param type
	 * @return
	 */
	public static Field[] getAllFields(Class<?> type) {
		List<Field> fields = new ArrayList<Field>();
		populateFields(type, fields);
		return fields.toArray(new Field[0]);
	}

	private static void populateFields(Class<?> type, List<Field> fields) {
		if (type != null && type != null) {
			Field[] declaredFields = type.getDeclaredFields();
			for (Field declaredField : declaredFields) {
				if (!Modifier.isStatic(declaredField.getModifiers())) {
					fields.add(declaredField);
				}
			}

			populateFields(type.getSuperclass(), fields);
		}
	}
}

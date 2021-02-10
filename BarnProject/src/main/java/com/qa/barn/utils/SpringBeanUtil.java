package com.qa.barn.utils;

import static org.springframework.beans.BeanUtils.copyProperties;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class SpringBeanUtil {

	// merge not null - it is used for ensuring we dont get an error when we our
	// update statement
	// we need to create a custom handler --> will handle our error if one occurs.

	// Methoddd
	public static void mergeNotNull(Object source, Object target) {
		copyProperties(source, target, getNullPropName(source));
	}

	private static String[] getNullPropName(Object src) {
		final BeanWrapper wrappedSrObj = new BeanWrapperImpl(src);
		// loop through the data we pass and then do something
		Set<String> propName = new HashSet<>();
		for (PropertyDescriptor descriptor : wrappedSrObj.getPropertyDescriptors()) {
			if (wrappedSrObj.getPropertyValue(descriptor.getName()) == null)
				propName.add(descriptor.getName());

		}
		return propName.toArray(new String[propName.size()]);

	}

	// this just check that our object is not empty during updates and prevents
	// spring from
	// dying.

}

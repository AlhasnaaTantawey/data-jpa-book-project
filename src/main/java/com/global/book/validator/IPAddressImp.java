package com.global.book.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IPAddressImp implements ConstraintValidator<IPAddress, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		 if (value == null || value.isEmpty()) {
	            return false;
	        }
	        // Updated regex to capture each octet in separate groups
	        Pattern pattern = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
	        Matcher matcher = pattern.matcher(value);

	        if (!matcher.matches()) {
	            return false;  // Does not match the IP address format
	        }

	        // Check each captured group (octet) and ensure they are in the range 0-255
	        try {
	            for (int i = 1; i <= 4; i++) {
	                int octet = Integer.parseInt(matcher.group(i));
	                if (octet < 0 || octet > 255) {
	                    return false;  // Octet is out of range
	                }
	            }
	            return true;
	        } catch (NumberFormatException e) {
	            return false;  // Failed to parse one of the octets
	        }
	    }

}

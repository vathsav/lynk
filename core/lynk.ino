/* Function prototypes -------------------------------------------------------*/
int lynkDigitalRead(String pin);
int lynkDigitalWrite(String command);
int lynkAnalogRead(String pin);
int lynkAnalogWrite(String command);

void setup() {
	//Register all the Lynk functions
	Spark.function("digitalRead", lynkDigitalRead);
	Spark.function("digitalWrite", lynkDigitalWrite);
	Spark.function("analogRead", lynkAnalogRead);
	Spark.function("analogWrite", lynkAnalogWrite);
	Spark.function("supermassiveEmp", lynkBlow);
}

void loop() {}

/*******************************************************************************
 * Function Name  : lynkDigitalRead
 * Description    : Reads the digital value of a given pin
 * Input          : Pin
 * Output         : None.
 * Return         : Value of the pin (0 or 1) in INT type
                    Returns a negative number on failure
 *******************************************************************************/
int lynkDigitalRead(String pin) {
	//convert ascii to integer
	int pinNumber = pin.charAt(1) - '0';
	//Sanity check to see if the pin numbers are within limits
	if (pinNumber < 0 || pinNumber > 7) return -1;

	if(pin.startsWith("D")) {
		pinMode(pinNumber, INPUT_PULLDOWN);
		return digitalRead(pinNumber);
	}
	else if (pin.startsWith("A")) {
		pinMode(pinNumber + 10, INPUT_PULLDOWN);
		return digitalRead(pinNumber + 10);
	}
	return -2;
}

/*******************************************************************************
 * Function Name  : lynkDigitalWrite
 * Description    : Sets the specified pin HIGH or LOW
 * Input          : Pin and value
 * Output         : None.
 * Return         : 1 on success and a negative number on failure
 *******************************************************************************/
int lynkDigitalWrite(String command) {
	bool value = 0;
	//convert ascii to integer
	int pinNumber = command.charAt(1) - '0';
	//Sanity check to see if the pin numbers are within limits
	if (pinNumber < 0 || pinNumber > 7) return -1;

	if(command.substring(3) == "1") value = 1;
	else if(command.substring(3) == "0") value = 0;
	else return -2;

	if(command.startsWith("D")) {
		pinMode(pinNumber, OUTPUT);
		digitalWrite(pinNumber, value);
		return 1;
	}
	else if(command.startsWith("A")) {
		pinMode(pinNumber + 10, OUTPUT);
		digitalWrite(pinNumber + 10, value);
		return 1;
	}
	else return -3;
}

/*******************************************************************************
 * Function Name  : lynkAnalogRead
 * Description    : Reads the analog value of a pin
 * Input          : Pin
 * Output         : None.
 * Return         : Returns the analog value in INT type (0 to 4095)
                    Returns a negative number on failure
 *******************************************************************************/
int lynkAnalogRead(String pin) {
	//convert ascii to integer
	int pinNumber = pin.charAt(1) - '0';
	//Sanity check to see if the pin numbers are within limits
	if (pinNumber < 0 || pinNumber > 7) return -1;

	if(pin.startsWith("D")) {
		return -3;
	}   
	else if (pin.startsWith("A")) {
		return analogRead(pinNumber + 10);
	}
	return -2;
}

/*******************************************************************************
 * Function Name  : lynkAnalogWrite
 * Description    : Writes an analog value (PWM) to the specified pin
 * Input          : Pin and Value (0 to 255)
 * Output         : None.
 * Return         : 1 on success and a negative number on failure
 *******************************************************************************/
int lynkAnalogWrite(String command) {
	//convert ascii to integer
	int pinNumber = command.charAt(1) - '0';
	//Sanity check to see if the pin numbers are within limits
	if (pinNumber < 0 || pinNumber > 7) return -1;

	String value = command.substring(3);

	if(command.startsWith("D")) {
		pinMode(pinNumber, OUTPUT);
		analogWrite(pinNumber, value.toInt());
		return 1;
	}
	else if(command.startsWith("A")) {
		pinMode(pinNumber + 10, OUTPUT);
		analogWrite(pinNumber + 10, value.toInt());
		return 1;
	}
	else return -2;
}

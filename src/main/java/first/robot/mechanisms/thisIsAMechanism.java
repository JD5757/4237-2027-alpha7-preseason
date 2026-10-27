package first.robot.mechanisms;

import java.util.function.DoubleSupplier;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;

import first.robot.motors.SparkMaxLance;

public class thisIsAMechanism implements Mechanism
{
    private static final String fullClassName = "thisIsAMechanism";

    static
    {
        System.out.println("Loading: " + fullClassName);
    }

    private final SparkMaxLance motor = new SparkMaxLance(0, null, null);
    private final SparkMaxLance motor2 = new SparkMaxLance(0, null, null);

    public thisIsAMechanism()
    {
        configMotors();
    }

    private void configMotors()
    {
        motor.setupFactoryDefaults();
        motor2.setupFactoryDefaults();
    }

    private void setThrottle(double speed)
    {
        motor.setThrottle(speed);
        motor2.setThrottle(speed);
    }
    
    public Command setForwardCommand(DoubleSupplier speed)
    {
        return 
            run(coroutine ->
                {
                    setThrottle(speed.getAsDouble());
                }
            ).named("");
    }

    
    
}

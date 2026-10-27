package first.robot.mechanisms;

import static first.robot.Constants.ExampleMechanism.*;

import java.lang.invoke.MethodHandles;
import java.util.function.DoubleSupplier;

import org.wpilib.math.util.*;

import org.wpilib.command3.Command;
import org.wpilib.command3.Coroutine;
import org.wpilib.command3.Mechanism;
import first.robot.motors.TalonFXLance;


/**
 * This is an example of what a subsystem should look like.
 */
// public class ExampleSubsystem extends SubsystemBase
public class ExampleMechanism implements Mechanism
{
    // This string gets the full name of the class, including the package name
    private static final String fullClassName = MethodHandles.lookup().lookupClass().getCanonicalName();

    // *** STATIC INITIALIZATION BLOCK ***
    // This block of code is run first when the class is loaded
    static
    {
        System.out.println("Loading: " + fullClassName);
    }
    

    // *** INNER ENUMS and INNER CLASSES ***
    // Put all inner enums and inner classes here


    
    // *** CLASS VARIABLES & INSTANCE VARIABLES ***
    // Put all class variables and instance variables here
    private final TalonFXLance motor1 = new TalonFXLance(MOTOR1, MOTOR_CAN_BUS, "Motor 1");
    private final TalonFXLance motor2 = new TalonFXLance(MOTOR2, MOTOR_CAN_BUS, "Motor 2");


    // *** CLASS CONSTRUCTORS ***
    // Put all class constructors here

    /** 
     * Creates a new ExampleMechanism. 
     */
    public ExampleMechanism()
    {
        super();
        System.out.println("  Constructor Started:  " + fullClassName);

        configMotors();

        System.out.println("  Constructor Finished: " + fullClassName);
    }


    // *** CLASS METHODS & INSTANCE METHODS ***
    // Put all class methods and instance methods here

    private void configMotors()
    {
        motor1.setupFactoryDefaults();
        motor2.setupFactoryDefaults();
    }

    /**
     * This sets the speed of the motors.
     * @param speed The motor speed (-1.0 to 1.0)
     */
    private void set(double speed)
    {
        motor1.set(speed);
        motor2.set(speed);
    }

    public void stop()
    {
        set(0.0);
        set(0.0);
    }

    public Command onCommand()
    {
        return Command.noRequirements(coroutine -> {
            set(0.5);
        }).named("onCommand");
    }

    public Command setCommand(DoubleSupplier speed)
    {
        return Command.noRequirements(coroutine -> {
            set(speed.getAsDouble());
        }).named("setCommand");
    }

    // Use a method reference instead of this method
    public Command stopCommand()
    {
        // return run( () -> stop() );
        return Command.noRequirements(coroutine -> this.stop()).named("stopCommand");
    }


    // *** OVERRIDEN METHODS ***
    // Put all methods that are Overridden here

    @Override
    public String toString()
    {
        return "";
    }
}
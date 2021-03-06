package org.usfirst.frc.team4145.robot.commands.testonly;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4145.robot.RobotMap;

public class HigherLiftTest extends Command {
    private boolean Test2pt1;
    private boolean Test2pt2;
    private double power = .5;
    private int iterations = 0;


    public void initialize() {
        RobotMap.liftMotorH.set(-power);
        Test2pt1 = false;
        Test2pt2 = false;
        iterations = 0;
    }

    @Override
    protected boolean isFinished() {
        return iterations == 1500;
    } // has to finish at end of auto routine

    public void execute() {
        iterations++;
        if (iterations <= 750 && RobotMap.liftMotorH.getSensorCollection().isRevLimitSwitchClosed()) {
            Test2pt1 = true;
            RobotMap.liftMotorH.set(power);
        }if (iterations >= 750 && RobotMap.liftMotorH.getSensorCollection().isFwdLimitSwitchClosed()) {
            Test2pt2 = true;
            RobotMap.liftMotorH.set(0);
        }
    }


    public void end() {

        RobotMap.liftMotorH.set(0);
        SmartDashboard.putBoolean("Higher Lift Test", Test2pt1 && Test2pt2);
    }

    public void interrupted() {
        end();
    }
}

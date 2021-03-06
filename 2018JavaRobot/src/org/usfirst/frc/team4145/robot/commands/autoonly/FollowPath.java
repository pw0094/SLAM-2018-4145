package org.usfirst.frc.team4145.robot.commands.autoonly;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4145.robot.RobotMap;
import org.usfirst.frc.team4145.robot.shared.AutoTrajectory.Path;

public class FollowPath extends Command {

    private Path mPath;
    private boolean mReversed, mHasStarted;

    public FollowPath(Path path, boolean reversed){
        mPath = path;
        mReversed = reversed;
        mHasStarted = false;
    }

    @Override
    public boolean isFinished() {
        boolean done = RobotMap.robotDriveV4.isFinishedPath() && mHasStarted;
        if (done) {
            System.out.println("Finished path");
        }
        return done;
    }

    @Override
    public void execute() {
        mHasStarted = true;
    }

    @Override
    public void end() {
        RobotMap.robotDriveV4.stop();
    }

    @Override
    public void initialize() {
        RobotMap.robotDriveV4.followPath(mPath, mReversed);
    }
}
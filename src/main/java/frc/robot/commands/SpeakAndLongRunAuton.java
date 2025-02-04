package frc.robot.commands;

import frc.robot.Constants.LauncherConstants;
import frc.robot.subsystems.CANLauncher;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class SpeakAndLongRunAuton extends SequentialCommandGroup {
    public SpeakAndLongRunAuton(Drivetrain drivetrain, CANLauncher launcher) {
        addCommands(
            new PrepareLaunch(launcher, drivetrain)
                .withTimeout(LauncherConstants.kLauncherDelay)
                .andThen(new LaunchNote(launcher, drivetrain))
                .withTimeout(2)
                .handleInterrupt(() -> launcher.stop()),
                new DriveTime(-.4, 2, drivetrain),
            new StraffeTime(0.4, 2.0, drivetrain),
            new TurnTime (-0.5, 1.5, drivetrain)
        );
    }
}

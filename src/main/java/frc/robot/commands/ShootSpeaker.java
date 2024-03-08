package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.subsystems.Shooter;

public class ShootSpeaker extends SequentialCommandGroup {
    public ShootSpeaker(Shooter shooter) {
        addCommands(
            new StartEndCommand(
                shooter::backfeed,
                shooter::stopFeed,
                shooter
            ).withTimeout(0.25),
            new ShooterSpinUp(shooter),
            new StartEndCommand(
                shooter::shoot,
                shooter::stop,
                shooter
            ).withTimeout(1)
        );
    }

    
}

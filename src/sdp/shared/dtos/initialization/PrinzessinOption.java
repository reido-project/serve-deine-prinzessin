package sdp.shared.dtos.initialization;

import sdp.content.prinzessins.Prinzessin;

import java.awt.image.BufferedImage;

public record PrinzessinOption(Prinzessin prinzessin, String name, BufferedImage sprite) {}

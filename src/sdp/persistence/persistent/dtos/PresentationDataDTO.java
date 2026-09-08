package sdp.persistence.persistent.dtos;

import sdp.modules.assets.Asset;

public record PresentationDataDTO(Asset currentMusic, Asset currentVoice, Asset currentSprite) {}
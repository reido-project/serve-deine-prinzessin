package sdp.content.gameplay.talk.requirements;

import sdp.modules.requirement.Requirement;
import sdp.persistence.api.StatDataAPI;
import sdp.persistence.api.specialized.TopicRequirementAPI;

public abstract class TopicRequirement implements Requirement, StatDataAPI, TopicRequirementAPI {}

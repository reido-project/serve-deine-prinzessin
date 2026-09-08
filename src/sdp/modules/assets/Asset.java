package sdp.modules.assets;

import sdp.Config;
import sdp.shared.exceptions.IllegalClassNameException;

import java.util.Arrays;
import java.util.List;

public interface Asset {
    List<String> BLACKLIST = List.of("sdp", "modules", "content", "assets");

    String getFileName();

    default AssetCategory getCategory() {
        String className = this.getClass().getSimpleName();

        for (AssetCategory category : AssetCategory.values()) {
            if (className.toUpperCase().endsWith(category.name())) {
                return category;
            }
        }

        throw new IllegalClassNameException(
            "Class name '" + className + "' doesn't follow naming convention (e.g. *Sprite, *Music, *Sfx)"
        );
    }

    private List<String> getFilteredPackageParts() {
        return Arrays.stream(this.getClass().getPackageName().split("\\."))
            .filter(part -> !BLACKLIST.contains(part))
            .toList();
    }

    default String getOwner() {
        List<String> parts = getFilteredPackageParts();
        return parts.isEmpty() ? "" : parts.getFirst();
    }

    default String getGroup() {
        List<String> parts = getFilteredPackageParts();
        return parts.size() > 1 ? parts.get(1) : "";
    }

    default String getDirectory() {
        List<String> parts = getFilteredPackageParts();
        String owner = parts.isEmpty() ? "" : parts.getFirst();
        String group = parts.size() > 1 ? parts.get(1) : "";

        return String.format("%s/%s/%s/%s/%s",
            Config.ASSETS_DIR,
            owner,
            group,
            getCategory().toString().toLowerCase(),
            getFileName()
        );
    }
}
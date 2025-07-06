package meshkit.config.catalog

/**
 * Data classes for Backstage Software Catalog Component entity, compatible with Hoplite.
 * Reference: https://backstage.io/docs/features/software-catalog/descriptor-format
 */

enum class EntityKind {
    Component,
    API,
    Resource,
    System,
    Domain,
    Location,
    User,
    Group,
    Template,
    Policy
}
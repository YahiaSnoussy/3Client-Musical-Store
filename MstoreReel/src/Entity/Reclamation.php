<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Reclamation
 *
 * @ORM\Table(name="reclamation")
 * @ORM\Entity
 */
class Reclamation
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_rec", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idRec;

    /**
     * @var int|null
     *
     * @ORM\Column(name="id_user", type="integer", nullable=true)
     */
    private $idUser;

    /**
     * @var int|null
     *
     * @ORM\Column(name="id_produit", type="integer", nullable=true)
     */
    private $idProduit;

    /**
     * @var string|null
     *
     * @ORM\Column(name="message", type="text", length=65535, nullable=true)
     */
    private $message;

    /**
     * @var string|null
     *
     * @ORM\Column(name="sujet", type="text", length=65535, nullable=true)
     */
    private $sujet;

    /**
     * @var int|null
     *
     * @ORM\Column(name="id_moderateur", type="integer", nullable=true)
     */
    private $idModerateur;


}
